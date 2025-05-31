<#import "../layout.ftlh" as main>
<@main.layout>

    <style>
        .profile-box {
            max-width: 1100px;
            margin: 30px auto;
            padding: 20px 30px;
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0,0,0,0.1);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            color: #333;
        }

        .section-title {
            font-size: 2rem;
            font-weight: 700;
            margin-bottom: 25px;
            color: #007bff;
            text-align: center;
            text-transform: uppercase;
            letter-spacing: 2px;
        }

        /* Navigation links */
        a {
            display: inline-block;
            margin: 0 12px 20px 0;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border-radius: 6px;
            text-decoration: none;
            font-weight: 600;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: #0056b3;
        }

        /* Bookings list */
        .bookings-list {
            max-width: 1000px;
            margin: 40px auto 20px;
        }

        .list-group-item {
            margin-bottom: 15px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.07);
            padding: 15px 25px;
            background: #fefefe;
            transition: background-color 0.3s ease;
            cursor: default;
        }

        .list-group-item:hover {
            background-color: #f8f9fa;
        }

        .list-group-item h5 {
            font-weight: 700;
            color: #007bff;
        }

        /* Flight table */
        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0 10px; /* Adds spacing between rows */
            margin-top: 20px;
        }

        thead tr {
            background-color: #007bff;
            color: #fff;
            text-transform: uppercase;
            font-size: 0.9rem;
            letter-spacing: 1px;
            border-radius: 12px;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            vertical-align: middle;
        }

        tbody tr {
            background: #ffffff;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
            border-radius: 10px;
            transition: transform 0.15s ease;
        }

        tbody tr:hover {
            transform: translateY(-3px);
            box-shadow: 0 6px 16px rgba(0,0,0,0.12);
        }

        /* Table cells last column - action link */
        tbody tr td:last-child a {
            background-color: #28a745;
            color: #fff;
            padding: 6px 14px;
            border-radius: 5px;
            font-size: 0.9rem;
            font-weight: 600;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        tbody tr td:last-child a:hover {
            background-color: #1e7e34;
        }

        /* Responsive for small screens */
        @media (max-width: 768px) {
            .profile-box {
                padding: 15px 20px;
            }
            table, thead, tbody, th, td, tr {
                display: block;
            }
            thead tr {
                display: none;
            }
            tbody tr {
                margin-bottom: 20px;
                box-shadow: none;
                border-radius: 8px;
                padding: 15px;
                background: #f9f9f9;
            }
            tbody tr td {
                padding: 8px 0;
                position: relative;
                padding-left: 50%;
                text-align: right;
                border: none;
                border-bottom: 1px solid #ddd;
            }
            tbody tr td::before {
                position: absolute;
                top: 8px;
                left: 15px;
                width: 45%;
                padding-right: 10px;
                white-space: nowrap;
                font-weight: 700;
                text-align: left;
                content: attr(data-label);
                color: #555;
            }
            tbody tr td:last-child {
                border-bottom: 0;
            }
        }

        /* Info alert */
        .alert-info {
            background-color: #e9f7fd;
            color: #31708f;
            padding: 15px 25px;
            border-radius: 8px;
            margin-top: 40px;
            font-size: 1rem;
            text-align: center;
            font-weight: 600;
        }

        .profile-box p {
            margin-top: 40px;
            font-style: italic;
            color: #666;
            font-size: 0.9rem;
        }
    </style>

    <div class="profile-box text-center">
        <div class="section-title">Личный кабинет</div>

          <#if authenticated ??>
              <a href="/admin/companies">Companies</a>
              <a href="/admin/create">Create Company</a>

          </#if>
         <#if userAuth??>
              <#if bookings?? && bookings?size gt 0>
                    <div class="bookings-list mt-5">
                        <h3 class="mb-4">Your Bookings</h3>
                        <div class="list-group">
                            <#list bookings as booking>
                                <div class="list-group-item list-group-item-action">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h5 class="mb-1">Booking #${booking.id}</h5>
                                        <small class="text-muted">${booking.time}</small>
                                    </div>
                                    <div class="row mt-2">
                                        <div class="col-md-6">
                                            <p class="mb-1"><strong>Flight:</strong> ${booking.ticket.flight.number}</p>
                                            <p class="mb-1"><strong>From:</strong> ${booking.ticket.flight.fromCity}</p>
                                            <p class="mb-1"><strong>To:</strong> ${booking.ticket.flight.toCity}</p>
                                        </div>
                                        <div class="col-md-6">
                                            <p class="mb-1"><strong>Departure:</strong> ${booking.ticket.flight.departureTime}</p>
                                            <p class="mb-1"><strong>Arrival:</strong> ${booking.ticket.flight.arrivalTime}</p>
                                            <p class="mb-1"><strong>Price:</strong> $${booking.ticket.price}</p>
                                        </div>
                                    </div>
                                    <div class="mt-2">
                                        <small class="text-muted">Booked by: ${booking.user.email}</small>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    </div>
                <#else>
                    <div class="alert alert-info mt-5">
                        You don't have any bookings yet.
                    </div>
                </#if>
                </#if>


        <#if company??>
            <a href="/company/createFlight">Create Flight</a>

            <h2>Available Flights</h2>

            <table>
                <thead>
                <tr>
                    <th>#</th>
                    <th>Flight Number</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Departure</th>
                    <th>Arrival</th>
                    <th>Company</th>
                    <th>Additional info</th>
                </tr>
                </thead>
                <tbody>
                <#list flights as flight>
                    <tr>
                        <td>${flight_index + 1}</td>
                        <td>${flight.number}</td>
                        <td>${flight.fromCity}</td>
                        <td>${flight.toCity}</td>
                        <td>${flight.departureTime}</td>
                        <td>${flight.arrivalTime}</td>
                        <td>${flight.company.name}</td>
                        <td>
                            <a href="/admin/tickets/${flight.id}">View Details</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </#if>


        <p> (${user.email})</p>

    </div>


</@main.layout>
