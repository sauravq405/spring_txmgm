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

##**Custom error response v2 (not-recommended in production):**
```
{
    "errorResponse": {
        "statusCode": 500,
        "errorMessage": "Cannot invoke \"String.getBytes()\" because \"s\" is null",
        "path": "/passenger/api/bookFlightTicket",
        "timestamp": "2025-01-22T14:07:26.389172",
        "className": "com.demo.tx.service.FlightBookingService",
        "methodName": "testExceptions",
        "packageName": "com.demo.tx.service",
        "lineNumber": 52,
        "stackTrace": [
            "com.demo.tx.service.FlightBookingService.testExceptions(FlightBookingService.java:52)",
            "com.demo.tx.service.FlightBookingService.bookFlightTicket(FlightBookingService.java:29)",
            "java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)",
            "java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)",
            "java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)",
            "java.base/java.lang.reflect.Method.invoke(Method.java:568)",
            "org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:359)",
            "org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:196)",
            "org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)",
            "org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:380)",
            "org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)",
            "org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)",
            "org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:727)",
            "com.demo.tx.service.FlightBookingService$$SpringCGLIB$$0.bookFlightTicket(<generated>)",
            "com.demo.tx.controller.FlightBookingController.bookFlightTicket(FlightBookingController.java:20)",
            "java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)",
            "java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)",
            "java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)",
            "java.base/java.lang.reflect.Method.invoke(Method.java:568)",
            "org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:257)",
            "org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:190)",
            "org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118)",
            "org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:986)",
            "org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:891)",
            "org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)",
            "org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1088)",
            "org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:978)",
            "org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)",
            "org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:914)",
            "jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)",
            "org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)",
            "jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)",
            "org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195)",
            "org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)",
            "org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)",
            "org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)",
            "org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)",
            "org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)",
            "org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)",
            "org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)",
            "org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)",
            "org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)",
            "org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)",
            "org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)",
            "org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)",
            "org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)",
            "org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)",
            "org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)",
            "org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)",
            "org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)",
            "org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)",
            "org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:483)",
            "org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115)",
            "org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)",
            "org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)",
            "org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344)",
            "org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:397)",
            "org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)",
            "org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:905)",
            "org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1741)",
            "org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)",
            "org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1190)",
            "org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)",
            "org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)",
            "java.base/java.lang.Thread.run(Thread.java:842)"
        ]
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
Command to random UUID String from MAC/Linux terminal 
```
uuidgen | tr 'a-f' 'A-F' | tr -d '-'

```
