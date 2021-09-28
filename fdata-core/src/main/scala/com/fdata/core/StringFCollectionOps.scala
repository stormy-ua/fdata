package com.fdata.core

trait StringFCollectionOps[FCollection[_], Coder[_]] {

  def saveAsTextFile(path: String): Unit

}
