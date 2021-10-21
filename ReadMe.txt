
*****H2 Console available at :
edit2
http://localhost:8080/h2-console/
JDBC URL: jdbc:h2:mem:testdb
username: sa
password: password

build app:
 ./mvnw clean package

run app :  java -jar target/transferserviceapp-0.0.1-SNAPSHOT.jar

1) Request 1:
Request URL: http://localhost:8080/natwest/transfers/initiate
Method: POST
Request: {
             "sourceAccountNumber": 111111,
             "destAccountNumber": 222222,
             "amount": 50
         }
Response: Successful Transfer of Amount : 50,
           Source AccNum: 111111 to Dest AccNum: 222222

2) Request 2:
Request URL: http://localhost:8080/natwest/transfers/initiate
Method: POST
Request: {
             "sourceAccountNumber": 3333,
             "destAccountNumber": 4444,
             "amount": 50
         }
Response:{
             "timestamp": "2021-10-17T21:13:43.898+00:00",
             "message": "Unable to find com.natwest.transferserviceapp.model.Account with id 3333"
         }

3)Request 3:

Request:
{
    "sourceAccountNumber": 111111,
    "destAccountNumber": 222222,
    "amount": 5000000
}
Response:
{
    "timestamp": "2021-10-17T21:14:11.942+00:00",
    "message": "Insufficient Balance in source account"
}



