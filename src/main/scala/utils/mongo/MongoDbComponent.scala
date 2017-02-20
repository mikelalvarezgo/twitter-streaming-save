package utils.mongo

import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.Imports._

trait MongoDbComponent {

  val mongoHost: String
  val mongoPort: Int
  val db: String

  lazy val mongoClient = MongoClient(mongoHost, mongoPort)
  lazy val database = mongoClient(db)

}

object MongoContext {
  type MongoHost = String
  type MongoPort = Int
  type Db = String
  type MongoCtxt = (MongoHost, MongoPort, Db)


}
