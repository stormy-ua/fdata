package com.fdata.scio.syntax

import com.fdata.core.ops._
import com.fdata.core.syntax.FCollectionSyntax
import com.fdata.scio.ScioFCollection
import com.fdata.scio.ops._
import com.spotify.scio.coders.{Coder => SCoder}

final class ScioFCollectionSyntax extends FCollectionSyntax[ScioFCollection, SCoder] {
  implicit def toFCollectionOps[T](coll: ScioFCollection[T])
  : FCollectionOps[ScioFCollection, SCoder, T] = new ScioFCollectionOps[T](coll)

  implicit def toKeyedFCollectionOps[K, V](coll: ScioFCollection[(K, V)])
  : KeyedFCollectionOps[ScioFCollection, SCoder, K, V] =
    new ScioKeyedFCollectionOps[K, V](coll)

  implicit def toStringFlinkFCollectionOps(coll: ScioFCollection[String])
  : StringFCollectionOps[ScioFCollection, SCoder] = new ScioStringFCollectionOps(coll)
}
