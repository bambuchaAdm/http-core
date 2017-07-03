/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.http

import play.api.libs.json.Writes

import scala.concurrent.{ExecutionContext, Future}

trait GetHttpTransport {
  def doGet(url: String)(implicit hc: HeaderCarrier): Future[HttpResponse]
}

trait DeleteHttpTransport {
  def doDelete(url: String)(implicit hc: HeaderCarrier): Future[HttpResponse]
}

trait PatchHttpTransport {
  def doPatch[A](url: String, body: A)(implicit rds: Writes[A], hc: HeaderCarrier): Future[HttpResponse]
}

trait PutHttpTransport {
  def doPut[A](url: String, body: A)(implicit rds: Writes[A], hc: HeaderCarrier): Future[HttpResponse]
}

trait PostHttpTransport {
  def doPost[A](url: String, body: A, headers: Seq[(String, String)])(implicit wts: Writes[A], hc: HeaderCarrier): Future[HttpResponse]

  def doPostString(url: String, body: String, headers: Seq[(String, String)])(implicit hc: HeaderCarrier): Future[HttpResponse]

  def doEmptyPost[A](url: String)(implicit hc: HeaderCarrier): Future[HttpResponse]

  def doFormPost(url: String, body: Map[String, Seq[String]])(implicit hc: HeaderCarrier): Future[HttpResponse]
}

trait HttpTransport extends GetHttpTransport with DeleteHttpTransport with PatchHttpTransport with PutHttpTransport with PostHttpTransport {

//  def doGet(url: String)(implicit hc: HeaderCarrier): Future[HttpResponse]

//  def doDelete(url: String)(implicit hc: HeaderCarrier): Future[HttpResponse]

//  def doPatch[A](url: String, body: A)(implicit rds: Writes[A], hc: HeaderCarrier): Future[HttpResponse]

//  def doPut[A](url: String, body: A)(implicit rds: Writes[A], hc: HeaderCarrier): Future[HttpResponse]

//  def doPost[A](url: String, body: A, headers: Seq[(String, String)])(implicit wts: Writes[A], hc: HeaderCarrier): Future[HttpResponse]
//
//  def doPostString(url: String, body: String, headers: Seq[(String, String)])(implicit hc: HeaderCarrier): Future[HttpResponse]
//
//  def doEmptyPost[A](url: String)(implicit hc: HeaderCarrier): Future[HttpResponse]
//
//  def doFormPost(url: String, body: Map[String, Seq[String]])(implicit hc: HeaderCarrier): Future[HttpResponse]

}

trait CoreGet {
  def get(url: String)(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse]
  def get(url: String, queryParams: Seq[(String, String)])(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse]
}

trait CoreDelete {
  def delete(url: String)(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse]
}

trait CorePatch {
  def patch[I](url: String, body: I)(implicit wts: Writes[I], hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse]
}

trait CorePut {
  def put[I](url: String, body: I)(implicit wts: Writes[I], hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse]
}

trait CorePost {

  def post[I](url: String, body: I, headers: Seq[(String, String)] = Seq.empty)(implicit wts: Writes[I], hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse]
  def postString(url: String, body: String, headers: Seq[(String, String)] = Seq.empty)(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse]
  def postForm(url: String, body: Map[String, Seq[String]])(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse]
  def postEmpty(url: String)(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse]
}



