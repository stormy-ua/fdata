package com.fdata.core

import com.fdata.core.coders.FCoderInstances
import com.fdata.core.syntax.FCollectionSyntax

trait FDataContext {

  type Coder[T]
  type FCollection[T]

  val syntax: FCollectionSyntax[FCollection, Coder]

  val coders: FCoderInstances[Coder]

  def parallelize[T: Coder](data: T*): FCollection[T]

  def run(): Unit
}
