<#import "../layout.ftlh" as main>
<@main.layout>

    <style>
        .bookings-list {
            max-width: 1000px;
            margin: 0 auto;
        }
        .list-group-item {
            margin-bottom: 10px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .list-group-item:hover {
            background-color: #f8f9fa;
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
            <a href="company/createFlight">Create Flight</a>
        </#if>


        <p> (${user.email})</p>

    </div>


</@main.layout>
