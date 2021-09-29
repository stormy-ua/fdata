package com.fdata.mock

import com.fdata.core.FDataContext
import com.fdata.core.coders.FCoderInstances
import com.fdata.core.syntax.FCollectionSyntax
import com.fdata.mock.coders.{MockCoder, MockCoderInstances}
import com.fdata.mock.syntax.MockFCollectionSyntax

final class MockFContext extends FDataContext {

  type Coder[T] = MockCoder[T]
  type FCollection[T] = List[T]

  val syntax: FCollectionSyntax[List, MockCoder] = new MockFCollectionSyntax(this)
  val coders: FCoderInstances[MockCoder] = MockCoderInstances

  def parallelize[T: MockCoder](data: T*): List[T] = data.toList

  def run(): Unit = ()
}
