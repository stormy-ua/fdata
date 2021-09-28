package com.fdata.core

trait FDataContext {

  type Coder[T]
  type FCollection[T]

  val syntax: FCollectionSyntax[FCollection, Coder]

  def parallelize[T: Coder](data: T*): FCollection[T]

  def run(): Unit
}
