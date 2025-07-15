![WhatsApp Image 2025-07-15 at 11 59 50_59c9381f](https://github.com/user-attachments/assets/a72b065d-dad0-4cbd-a288-d07fc0781e22)
![WhatsApp Image 2025-07-15 at 12 00 06_a82eefcb](https://github.com/user-attachments/assets/de2ddb80-802d-4780-a385-f4e800b19c8f)
![WhatsApp Image 2025-07-15 at 12 00 56_1fddd95a](https://github.com/user-attachments/assets/e795eb09-dd52-4b22-abcb-408d6b746170)
<img width="1796" height="768" alt="image" src="https://github.com/user-attachments/assets/4d5607e6-c055-4213-a4e6-61b79624a43c" />
<img width="1818" height="575" alt="image" src="https://github.com/user-attachments/assets/e5236fe7-4c04-40b4-bb08-ee8e18b482db" />

**System Design Document – URL Shortener Microservice**
1. Objective
- The goal of this project is to implement a backend microservice that provides URL shortening functionality with features like custom shortcodes, expiry mechanism, logging via external middleware, and basic statistics tracking. The system is designed to be simple, scalable, and easy to integrate.
________________________________________
2. Technology Stack
Component	Choice	Reason
- Backend	Java (Spring Boot)	Industry-standard, supports clean layering and fast REST development Database	PostgreSQL	Reliable relational DB, good for structured data ORM	Spring Data JPA	Reduces boilerplate, integrates well with Spring Boot Logging	External LoggingService	Keeps core service clean, decouples concerns Build Tool	Maven	Handles dependency and project structure cleanly
________________________________________
3. Key Design Decisions
- Shortcode Generation
-	UUID substring used for random shortcodes
-	Custom shortcodes allowed if not already taken
-	Validity period enforced with default fallback (30 mins)
Logging
-	Integrated a separate LoggingService for tracking every key event
-	Logs are posted via RestTemplate in JSON format
-	Keeps actual business logic free from audit responsibilities
Expiry Handling
-	Each short URL has a strict expiry timestamp
-	If accessed after expiry, service throws an error and logs the event
Statistics Tracking
-	Clicks are tracked in a dedicated ClickLog table
-	Each record stores timestamp, referrer, and location (coarse)
API Design
-	Clean use of DTOs
-	201 returned for resource creation, proper use of status codes overall
-	JSON responses follow minimal and focused structure
________________________________________
4. Data Models
ShortUrl
- Field	Type	Purpose
- shortcode	String (PK)	Unique identifier
- longUrl	String	Original full URL
- createdAt	LocalDateTime	Timestamp of creation
- expiryAt	LocalDateTime	Link validity expiration
ClickLog
- Field	Type	Purpose
- id	Long (PK)	Unique row identifier
- shortcode	String	Foreign key to ShortUrl
- timestamp	LocalDateTime	When the click occurred
- referrer	String	Source of click
- location	String	Coarse location (e.g. city/country)
________________________________________


