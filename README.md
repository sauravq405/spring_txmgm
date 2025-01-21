## **Optional Info:**

##**create a new repository on the command line:**
```
echo "# spring_txmgm" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/sauravq405/spring_txmgm.git
git push -u origin main
```

##**push an existing repository from the command line:**

```
git remote add origin https://github.com/sauravq405/spring_txmgm.git
git branch -M main
git push -u origin main
```

##**Custom error response:**
```
{
    "errorResponse": {
        "statusCode": 400,
        "errorMessage": "Insufficient funds in account balance",
        "path": "/passenger/api/bookFlightTicket",
        "timestamp": "2025-01-21T22:55:29.913457"
    }
}
```


## **Mandatory Info:**
{Valid API URL and request body to create an employee.}


http://localhost:8090/passenger/api/bookFlightTicket

Request: 
```
{
    "passengerInfo": {
        "name": "saurav",
        "email": "saurav@gmail.com",
        "source": "Bangalore",
        "destination": "Mumbai",
        "travelDate": "16-10-2025",
        "pickupTime": "2.0 PM",
        "arrivalTime": "5.0 PM",
        "fare": 1800.0
    },
    "paymentInfo": {
        "accountNo": "acc1",
        "cardType": "DEBIT"
    }
}
```
H2 Console: http://localhost:8090/passenger/h2-console/

TABLE QUERIES:
```
select * from PASSENGER_INFO;

select * from PAYMENT_INFO;
```