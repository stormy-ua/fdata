package com.fdata.mock.syntax

import com.fdata.core.ops._
import com.fdata.core.syntax.FCollectionSyntax
import com.fdata.mock.MockFContext
import com.fdata.mock.coders.MockCoder
import com.fdata.mock.ops._

final class MockFCollectionSyntax(ctx: MockFContext) extends FCollectionSyntax[List, MockCoder] {
  implicit def toFCollectionOps[T](coll: List[T])
  : FCollectionOps[List, MockCoder, T] = new MockFCollectionOps[T](coll)

  implicit def toKeyedFCollectionOps[K, V](coll: List[(K, V)])
  : KeyedFCollectionOps[List, MockCoder, K, V] =
    new MockKeyedFCollectionOps[K, V](coll)

  implicit def toStringFlinkFCollectionOps(coll: List[String])
  : StringFCollectionOps[List, MockCoder] = new MockStringFCollectionOps(ctx, coll)
}
