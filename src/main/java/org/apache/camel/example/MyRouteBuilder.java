/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.builder.endpoint.dsl.AWS2S3EndpointBuilderFactory;
import org.springframework.stereotype.Component;
//import org.apache.camel.component.aws2.s3.stream.AWSS3NamingStrategyEnum;

@Component
public class MyRouteBuilder extends RouteBuilder {
        //EndpointRouteBuilder {

    @Override
    public void configure() throws Exception {

        String camelBucket = "kafkatos3bucket1";
       /* String AccessKey = "AKIA6ODU227GNSP6JW4Q";
        String SecretKey = "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";
        String Region = "us-east-1";*/


        /*from(kafka("{{myTopic}}").brokers("{{kafkaBrokers}}"))
              .log("Kafka Message is: ${body}")
              .to(aws2S3("{{bucketName}}").useDefaultCredentialsProvider(true).autoCreateBucket(true).streamingUploadMode(true).batchMessageNumber(25).keyName("{{kafkaTopic1}}/{{kafkaTopic1}}.txt"));

        from(kafka("{{kafkaTopic2}}").brokers("{{kafkaBrokers}}"))
                .log("Kafka Message is: ${body}")
                .to(aws2S3("{{bucketName}}").useDefaultCredentialsProvider(true).autoCreateBucket(true).streamingUploadMode(true).batchMessageNumber(25).keyName("{{kafkaTopic2}}/{{kafkaTopic2}}.txt"));
    }
    //.namingStrategy(AWSS3NamingStrategyEnum.progressive)*/
        /*from("kafka:codeDecode-Topic1?brokers=localhost:9092")
                .convertBodyTo(String.class) // Convert message body to String
                .to("aws-s3://kafkatos3bucket1?accessKey=AKIA6ODU227GGR63JVXA&secretKey=vXx1aMBarhNuz3LIsMaaZd4TLEFBvZYBNR/eD7eD&region=us-east-1&autoCreateBucket=true"); // Replace placeholders with your AWS credentials and region
      */  from("kafka:codeDecode-Topic1?brokers=localhost:9092")
                .log("Message sent to from topic: ${body}")
                .process(exchange -> {
                    // Process the message here if needed
                    String messageBody = exchange.getIn().getBody(String.class);
                    // Example: Transforming the message to uppercase
                    String transformedMessage = messageBody.toUpperCase();
                    exchange.getIn().setBody(transformedMessage);
                })
                .to("aws2-S3://(camelBucket).streamingUploadMode(true).batchMessageNumber(25)")

        // .to("aws2-s3://kafkatos3bucket1?accessKey=AKIA6ODU227GGR63JVXA&secretKey=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&region=us-east-1&autoCreateBucket=true")
                .log("Message sent to S3 bucket");
    }
    }

