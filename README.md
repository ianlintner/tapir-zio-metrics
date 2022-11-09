ZIO Metrics for Tapir https://github.com/softwaremill/tapir

## Collection Setup Example

Setting the metricsInterceptor value to ZioMetrics instance.
```scala
    val zioMetrics: ZioMetrics[Task] = ZioMetrics.default[Task]()

    val serverOptions: Http4sServerOptions[Task] =
      Http4sServerOptions
        .customiseInterceptors[Task]
        .metricsInterceptor(zioMetrics.metricsInterceptor())
        .options

```

## Example collection & Publishing via ZIO

Example metrics server publishing zio metrics using zio-metrics-connector module.

See the guide
https://zio.dev/guides/tutorials/monitor-a-zio-application-using-zio-built-in-metric-system

If you want to use prometheus style /metrics http publishing you will need to use ZIO Metrics Connectors.
```scala
libraryDependencies += "dev.zio" %% "zio-metrics-connectors" % "2.0.0-RC6"
```

```scala

    val layers = DefaultJvmMetrics.live.orDie >+> ZLayer.make[PrometheusPublisher](
      ZLayer.succeed(MetricsConfig(1.seconds)),
      prometheusLayer,
      publisherLayer
    )
    val unsafeLayers = Unsafe.unsafe { implicit u =>
        Runtime.unsafe.fromLayer(layers)
      }

    def getMetricsEffect: ZIO[Any, Nothing, String] =
      Unsafe.unsafe { implicit u =>
        unsafeLayers.run(ZIO
          .serviceWithZIO[PrometheusPublisher](_.get)
        )
      }


    val metricsEndpoint: ZServerEndpoint[Any, Any] = endpoint.get.in("metrics").out(stringBody).serverLogicSuccess(_ => getMetricsEffect)
    val zioMetrics: ZioMetrics[Task] = ZioMetrics.default[Task]()

    val serverOptions: Http4sServerOptions[Task] =
      Http4sServerOptions
        .customiseInterceptors[Task]
        .metricsInterceptor(zioMetrics.metricsInterceptor())
        .options

    val routes = ZHttp4sServerInterpreter(serverOptions).from(Endpoints.all ++ List(metricsEndpoint)).toRoutes

```
