// Copyright (c) 2017-2019 Twitter, Inc.
// Licensed under the Apache License, Version 2.0 (see LICENSE.md).
package rsc.bench

import java.util.concurrent.TimeUnit
import java.nio.file.Files
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.annotations.Mode._

@BenchmarkMode(Array(SampleTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgs = Array("-Xms4G", "-Xmx4G"))
class YoutlineCompile {
  @Benchmark
  def run(bs: BenchmarkState): Unit = {
    val compile = new ScalacCompile()    
    compile.settings.Youtline.value = true
    compile.settings.stopAfter.value = List("pickler")
    compile.settings.YpickleWrite.value = Files.createTempDirectory("youtline").toString()
    compile.run(bs)
  }
}
