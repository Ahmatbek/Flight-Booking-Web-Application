**# ✈️ Flight Booking Web Application

A full-stack web application built with **Spring Boot**, **Spring Security**, **FreeMarker**, and **PostgreSQL** for booking flight tickets. The platform supports different user roles (Passengers, Travel Companies, and Admin), and offers secure authentication, role-based access, filtering, pagination, and booking workflows.

## 🚀 Features

### 🔍 Public Functionality
- Flight search by:
  - Departure city
  - Arrival city
  - Departure and return date (range selection)
- Paginated ticket list with:
  - Company name and logo
  - Departure and arrival time
  - Route and price
  - “Book” button (redirects to login if not authenticated)

### 👤 User Functionality (Passengers)
- Register & login (email, full name, password)
- Book available tickets (choose class and seat)
- View booking history in profile
- Seat becomes unavailable after booking

### 🏢 Travel Company Functionality
- Managed by admin (cannot self-register)
- Upload company logo
- Create flights with:
  - Unique flight number
  - Departure/arrival cities and times
- Auto-generate 10 tickets per flight (7 economy, 3 business)
- View own flights and generated tickets

### 🔐 Admin Panel
- Accessible at `/admin`
- Login: `admin`, Password: `admin`
- Create travel companies
- Freeze/unfreeze companies (only if no active bookings)
- View companies and their flights
- View number of bookings per flight

## 🛠 Tech Stack

- **Backend**: Java, Spring Boot, Spring Security, Spring Data JPA
- **Frontend**: FreeMarker, HTML, CSS, JavaScript
- **Database**: PostgreSQL, H2 (for testing)
- **Auth**: Session-based login
- **DevOps**: Git, Swagger, Postman

## 🧪 Initial Demo Data

- Pre-loaded users with bookings
- Sample flights and tickets
- Travel companies with logos
