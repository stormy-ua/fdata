# F-Data

> Functional Big Data Processing API

## Features

* *Portable*. Define your job once and run it with different interpreters
* *Idiomatic*. Functional type-driven API design
* *Lightweight*. As little overhead as possible. Interpreters translate a job directly into a 
  native data processing framework API
* *Reasonable*. Low cognitive friction to understand and debug an interpreter

## Interpreters

* [Apache Flink](fdata-flink/src/main/scala/com/fdata/flink)
* [Scio/Apache Beam](fdata-scio/src/main/scala/com/fdata/scio)
* [Mock/Test](fdata-mock/src/main/scala/com/fdata/mock)

## Quickstart

[WordCount](fdata-examples/src/main/scala/com/fdata/examples/WordCountJob.scala) example.