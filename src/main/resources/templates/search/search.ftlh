<#import "../layout.ftlh" as main>
<@main.layout; spring>
<style>
    .search-bar {
        display: flex;
        flex-wrap: wrap;
        gap: 15px;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 20px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        background: white;
        max-width: 1100px;
        margin: 30px auto;
    }

    .search-bar input,
    .search-bar select {
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 10px;
        min-width: 200px;
        flex: 1;
    }

    .search-bar button {
        background-color: #ff6600;
        color: white;
        border: none;
        padding: 12px 20px;
        border-radius: 50px;
        cursor: pointer;
        font-size: 18px;
    }

    .search-bar button:hover {
        background-color: #e65c00;
    }

    .search-group {
        display: flex;
        flex-direction: column;
    }

    .search-group label {
        margin-bottom: 4px;
        font-weight: bold;
        font-size: 14px;
    }

    .results-section {
        margin-top: 30px;
        padding: 20px;
    }

    .flight-card {
        background: white;
        border-radius: 10px;
        padding: 20px;
        margin-bottom: 15px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .flight-card h3 {
        margin: 0 0 10px 0;
        color: #333;
    }

    .flight-details {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
    }

    .flight-info {
        flex: 1;
    }

    .flight-price {
        font-size: 1.2em;
        font-weight: bold;
        color: #ff6600;
    }

    .book-button {
        background-color: #ff6600;
        color: white;
        border: none;
        padding: 8px 16px;
        border-radius: 5px;
        cursor: pointer;
    }

    .book-button:hover {
        background-color: #e65c00;
    }

    .booking-details {
        padding: 15px;
        background-color: #f8f9fa;
        border-radius: 5px;
        margin-bottom: 20px;
    }

    .booking-details h4 {
        color: #333;
        margin-bottom: 15px;
    }

    .booking-details p {
        margin-bottom: 8px;
    }

    .modal-content {
        border-radius: 10px;
    }

    .modal-header {
        background-color: #f8f9fa;
        border-bottom: 1px solid #dee2e6;
    }

    .modal-footer {
        background-color: #f8f9fa;
        border-top: 1px solid #dee2e6;
    }
</style>

<form action="/flights/search" method="post" class="search-bar">
    <#if _csrf??>
        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
               value="${(_csrf.token)!'csrf-token'}"/>
    </#if>
    <div class="search-group">
        <label for="departure">Departure</label>
        <@spring.formInput "searchDto.fromCity" 'id="departure" class="form-control form-control-lg"' />
        <@spring.showErrors "<br>" "error text-danger" />
    </div>

    <div class="search-group">
        <label for="destination">Destination</label>
        <@spring.formInput "searchDto.toCity" 'id="destination" class="form-control form-control-lg"' />
        <@spring.showErrors "<br>" "error text-danger" />
    </div>

    <div class="search-group">
        <label for="departureTime">When?</label>
        <@spring.formInput "searchDto.departureTime" 'id="departureTime" class="form-control form-control-lg"' 'date' />
        <@spring.showErrors "<br>" "error text-danger" />
    </div>
    <div class="search-group">
        <label for="departureTime">When?</label>
        <@spring.formInput "searchDto.arrivalTime" 'id="departureTime" class="form-control form-control-lg"' 'date' />
        <@spring.showErrors "<br>" "error text-danger" />
    </div>

    <div class="search-group">
        <label for="ticketsClass">Passengers / Class</label>
        <select id="ticketsClass" name="ticketClass">
            <option value="ECONOMY">Economy</option>
            <option value="BUSINESS">Business</option>
        </select>
    </div>

    <button type="submit">🔍</button>
</form>

<#if tickets?? && tickets?size gt 0>
    <div class="results-section">
        <h2>Available Flights</h2>
        <#list tickets as ticket>
            <div class="flight-card" data-ticket-id="${ticket.id}">
                <h3>Flight ${ticket.flight.number}</h3>
                <div class="flight-details">
                    <div class="flight-info">
                        <p><strong>From:</strong> ${ticket.flight.fromCity}</p>
                        <p><strong>To:</strong> ${ticket.flight.toCity}</p>
                        <p><strong>Departure:</strong> ${ticket.flight.departureTime}</p>
                        <p><strong>Arrival:</strong> ${ticket.flight.arrivalTime}</p>
<#--                        <p><strong>Class:</strong> ${ticket.ticketClassName}</p>-->
                    </div>
                    <div class="flight-price">
                        $${ticket.price}
                    </div>
                </div>
                <button class="book-button" 
                        data-ticket-id="${ticket.id}"
                        data-flight-number="${ticket.flight.number}"
                        data-from-city="${ticket.flight.fromCity}"
                        data-to-city="${ticket.flight.toCity}"
                        data-departure="${ticket.flight.departureTime}"
                        data-arrival="${ticket.flight.arrivalTime}"
                        data-price="${ticket.price}">
                    Book Now
                </button>
            </div>
        </#list>
    </div>
<#elseif tickets??>
    <div class="results-section">
        <p>No flights found matching your criteria.</p>
    </div>
</#if>

<div class="modal fade" id="bookingModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirm Booking</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="bookingForm">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}"/>
                    </#if>
                    <input type="hidden" id="ticketId" name="ticketId">
                    <div class="booking-details">
                        <h4>Flight Details</h4>
                        <p><strong>Flight Number:</strong> <span id="modalFlightNumber"></span></p>
                        <p><strong>From:</strong> <span id="modalFromCity"></span></p>
                        <p><strong>To:</strong> <span id="modalToCity"></span></p>
                        <p><strong>Departure:</strong> <span id="modalDeparture"></span></p>
                        <p><strong>Arrival:</strong> <span id="modalArrival"></span></p>
                        <p><strong>Price:</strong> $<span id="modalPrice"></span></p>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary" id="confirmBooking">Confirm Booking</button>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const bookingModal = new bootstrap.Modal(document.getElementById('bookingModal'));
        const bookingForm = document.getElementById('bookingForm');

        document.querySelectorAll('.book-button').forEach(button => {
            button.addEventListener('click', function() {
                const ticketId = this.dataset.ticketId;
                const flightNumber = this.dataset.flightNumber;
                const fromCity = this.dataset.fromCity;
                const toCity = this.dataset.toCity;
                const departure = this.dataset.departure;
                const arrival = this.dataset.arrival;
                const price = this.dataset.price;

                document.getElementById('ticketId').value = ticketId;
                console.log(document.getElementById('ticketId').value )
                document.getElementById('modalFlightNumber').textContent = flightNumber;
                document.getElementById('modalFromCity').textContent = fromCity;
                document.getElementById('modalToCity').textContent = toCity;
                document.getElementById('modalDeparture').textContent = departure;
                document.getElementById('modalArrival').textContent = arrival;
                document.getElementById('modalPrice').textContent = price;

                bookingModal.show();
            });
        });

        document.getElementById('confirmBooking').addEventListener('click', function() {
            let ticketId = document.getElementById('ticketId').value;
            window.location.href = `/booking/`+ticketId;
        });
    });
</script>

</@main.layout>