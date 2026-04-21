# Simple Java HTTP Server (Echo & Inquiry API)

This project is a lightweight Java 8 HTTP server built using `com.sun.net.httpserver.HttpServer`.
It is designed for learning, simulation, and basic API testing without external frameworks.

---

# 📌 Overview

The server provides two main APIs:

1. **Echo API** → simple request-response validation
2. **Inquiry API** → simulated payment inquiry with additional merchant data

It also includes:

* Configurable success rate (simulate success/failure behavior)
* Simple ISO8583-like response codes
* Basic JSON parsing (manual, regex-based)

---

# 🚀 How to Run

## Compile

```bash
javac -d out src/**/*.java
```

## Run server

```bash
java -cp out MainServer
```

Server runs on:

```text
http://localhost:8080
```

---

# ⚙️ Configuration

Success rate is controlled via:

```java
AppConfig.SUCCESS_RATE = 80;
```

Meaning:

* 80% → success (`00`)
* 20% → failure (`05`, `12`, `91`)

---

# 📡 API Documentation (Postman Ready)

---

# 1. Echo API

## Endpoint

```http
POST /echo
```

---

## Request Body

```json
{
  "transDateTime": "2025-08-10 12:00:13"
}
```

---

## Response Body

```json
{
  "transDateTime": "2025-08-10 12:00:13",
  "responseCode": "00"
}
```

---

## Example (Postman)

* Method: `POST`
* URL:

```
http://localhost:8080/echo
```

* Body → raw → JSON:

```json
{
  "transDateTime": "2025-08-10 12:00:13"
}
```

---

# 2. Inquiry API

## Endpoint

```http
POST /inquiry
```

---

## Request Body

```json
{
  "transDateTime": "2025-08-10 12:00:13",
  "qrCode": "sdsakfuasfwa",
  "senderAccount": "123243256",
  "senderInstitutionCode": "008"
}
```

---

## Response Body

```json
{
  "transDateTime": "2025-08-10 12:00:13",
  "qrCode": "sdsakfuasfwa",
  "senderAccount": "123243256",
  "senderInstitutionCode": "008",
  "responseCode": "00",
  "additionalDataResp": {
    "merchantNumber": "xx",
    "merchantName": "abc",
    "infoA": "etc",
    "infoB": "etc"
  }
}
```

---

## Example (Postman)

* Method: `POST`
* URL:

```
http://localhost:8080/inquiry
```

* Body → raw → JSON:

```json
{
  "transDateTime": "2025-08-10 12:00:13",
  "qrCode": "sdsakfuasfwa",
  "senderAccount": "123243256",
  "senderInstitutionCode": "008"
}
```

---

# 📊 Response Code Behavior

| Code | Meaning             |
| ---- | ------------------- |
| 00   | Success             |
| 05   | Do not honor        |
| 12   | Invalid transaction |
| 91   | System error        |

---

# 🧠 Architecture Notes

* Each endpoint has its own handler class:

  * `EchoHandler`
  * `InquiryHandler`
* Designed for easy extension:

  * Add new endpoint → create new handler class → register in `MainServer`

---

# 📁 Project Structure

```text
src/
 ├── MainServer.java
 ├── config/
 │    └── AppConfig.java
 ├── handler/
 │    ├── EchoHandler.java
 │    └── InquiryHandler.java
 └── util/
      └── ResponseUtil.java
```

---

# 🔧 Limitations

This project is intentionally simple:

* No JSON library (manual parsing)
* No authentication
* No persistence layer
* No validation framework

---

# 🎯 Purpose

This project is useful for:

* Learning raw Java HTTP server
* Simulating payment APIs
* Testing integration flows (Postman / automation tools)
* Practicing SLO / error simulation logic

---

# 🚀 Future Improvements

Possible upgrades:

* Replace manual JSON parsing with Jackson/Gson
* Add logging framework
* Add metrics endpoint for SLO monitoring
* Add database persistence
* Add authentication layer

---

# 🧪 Quick Test Flow

1. Start server
2. Call `/echo`
3. Call `/inquiry`
4. Observe random success/failure behavior
5. Adjust `SUCCESS_RATE` to simulate system stability

---

Built for learning, experimentation, and understanding how simple payment-like APIs behave under controlled conditions.
