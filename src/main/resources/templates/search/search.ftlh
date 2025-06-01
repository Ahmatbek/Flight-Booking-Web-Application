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


    .autocomplete-suggestions {
        position: absolute;
        top: 100%;
        left: 0;
        right: 0;
        background: white;
        border: 1px solid #ccc;
        max-height: 180px;
        overflow-y: auto;
        width: 100%;
        z-index: 1000;
        cursor: pointer;
        border-radius: 6px;
        box-sizing: border-box;
    }

    .autocomplete-suggestions {
        display: none;
    }


    .autocomplete-suggestion {
        padding: 8px 12px;
    }

    .autocomplete-suggestion:hover {
        background-color: #ff6600;
        color: white;
    }



</style>

<form action="/flights/search" method="get" class="search-bar">
    <#if _csrf??>
        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
               value="${(_csrf.token)!'csrf-token'}"/>
    </#if>
    <div class="search-group" style="position: relative;">
        <label for="departure">Departure</label>
        <input type="text" id="departure" name="fromCity" class="form-control form-control-lg" autocomplete="off"/>
        <div id="departureSuggestions" class="autocomplete-suggestions"></div>
    </div>


    <div class="search-group" style="position: relative;">
        <label for="destination">Destination</label>
        <input type="text" id="destination" name="toCity" class="form-control form-control-lg" autocomplete="off"/>
        <div id="destinationSuggestions" class="autocomplete-suggestions"></div>
    </div>

    <div class="search-group">
    <label for="datetimeRange">Departure & Arrival</label>
    <input id="datetimeRange" name="datetimeRange" class="form-control" placeholder="Select date & time range" required>
    </div>
    <div class="search-group">
        <label for="ticketsClass">Passengers / Class</label>
        <select id="ticketsClass" name="ticketClass">
            <option value="Economy">Economy</option>
            <option value="Business">Business</option>
        </select>
    </div>

    <button type="submit">🔍</button>
</form>
    <#if tickets?? && tickets.items?size gt 0>
        <div class="results-section">
            <h2>Available Flights</h2>
            <#list tickets.items as ticket>
                <div class="flight-card" data-ticket-id="${ticket.id}">
                    <h3>Flight ${ticket.flight.number}</h3>
                    <div class="flight-details">
                        <div class="flight-info">
                            <p><strong>From:</strong> ${ticket.flight.fromCity}</p>
                            <p><strong>To:</strong> ${ticket.flight.toCity}</p>
                            <p><strong>Departure:</strong> ${ticket.flight.departureTime}</p>
                            <p><strong>Arrival:</strong> ${ticket.flight.arrivalTime}</p>
                            <p><strong>Class:</strong> ${ticket.ticketClass}</p>
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
                            data-price="${ticket.price}"
                            data-ticket-class="${ticket.ticketClass}">
                        Book Now
                    </button>
                </div>
            </#list>

            <#if tickets?? && tickets.totalPages?? && tickets.totalPages gt 1>
                <nav class="pagination mt-4">
                    <ul class="pagination justify-content-center">
                        <li class="page-item <#if !tickets.hasPreviousPage>disabled</#if>">
                            <a class="page-link" href="?page=${(tickets.page!0) - 1}&fromCity=${searchDto.fromCity!''}&toCity=${searchDto.toCity!''}&datetimeRange=${searchDto.datetimeRange!''}&ticketClass=${searchDto.ticketClass!''}">&laquo; Prev</a>
                        </li>

                        <#list 1..tickets.totalPages as pageNum>
                            <li class="page-item <#if (tickets.page!0) == pageNum - 1>active</#if>">
                                <a class="page-link" href="?page=${pageNum - 1}&fromCity=${searchDto.fromCity!''}&toCity=${searchDto.toCity!''}&datetimeRange=${searchDto.datetimeRange!''}&ticketClass=${searchDto.ticketClass!''}">${pageNum}</a>
                            </li>
                        </#list>
                        <li class="page-item <#if !tickets.hasNextPage>disabled</#if>">
                            <a class="page-link" href="?page=${(tickets.page!0) + 1}&fromCity=${searchDto.fromCity!''}&toCity=${searchDto.toCity!''}&datetimeRange=${searchDto.datetimeRange!''}&ticketClass=${searchDto.ticketClass!''}">Next &raquo;</a>
                        </li>
                    </ul>
                </nav>
            </#if>


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
                        <p><strong>Class:</strong> <span id="modalTicketClass"></span></p>
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
    const dateRangeInput = document.getElementById('datetimeRange');

    flatpickr(dateRangeInput, {
        mode: "range",
        enableTime: true,
        dateFormat: "Y-m-d H:i",
        time_24hr: true,
        minDate: "today"
    });

    document.querySelector('.search-bar').addEventListener('submit', function (event) {
        const selectedDates = dateRangeInput.value.split(" to ");
        if (selectedDates.length !== 2 || !selectedDates[0] || !selectedDates[1]) {
            event.preventDefault();
            alert("Please select both departure and arrival date & time.");
            return false;
        }
    });

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
                const ticketClass = this.dataset.ticketClass;

                document.getElementById('ticketId').value = ticketId;
                document.getElementById('modalFlightNumber').textContent = flightNumber;
                document.getElementById('modalFromCity').textContent = fromCity;
                document.getElementById('modalToCity').textContent = toCity;
                document.getElementById('modalDeparture').textContent = departure;
                document.getElementById('modalArrival').textContent = arrival;
                document.getElementById('modalPrice').textContent = price;
                document.getElementById('modalTicketClass').textContent = ticketClass;

                bookingModal.show();
            });
        });

        document.getElementById('confirmBooking').addEventListener('click', function() {
            let ticketId = document.getElementById('ticketId').value;
            window.location.href = `/booking/`+ticketId;
        });
    });




    document.addEventListener('DOMContentLoaded', () => {
        const departureInput = document.getElementById('departure');
        const destinationInput = document.getElementById('destination');
        const departureSuggestions = document.getElementById('departureSuggestions');
        const destinationSuggestions = document.getElementById('destinationSuggestions');

        let citiesCache = null;

        async function fetchCities() {
            if (citiesCache) return citiesCache;
            try {
                const response = await fetch('/destinations');
                if (response.ok) {
                    citiesCache = await response.json();
                    return citiesCache;
                }
            } catch (e) {
                console.error('Failed to fetch cities', e);
            }
            return [];
        }

        function filterCities(inputVal, cities) {
            const val = inputVal.trim().toLowerCase();
            if (!val) return [];
            return cities.filter(city => city.toLowerCase().includes(val));
        }

        function renderSuggestions(container, suggestions, inputElem) {
            container.innerHTML = '';
            if (suggestions.length === 0) {
                container.style.display = 'none';
                return;
            }
            suggestions.forEach(city => {
                const div = document.createElement('div');
                div.classList.add('autocomplete-suggestion');
                div.textContent = city;
                div.addEventListener('click', () => {
                    inputElem.value = city;
                    container.style.display = 'none';
                });
                container.appendChild(div);
            });
            container.style.display = 'block';
        }

        function setupAutocomplete(inputElem, suggestionContainer) {
            inputElem.addEventListener('input', async () => {
                const cities = await fetchCities();
                const matches = filterCities(inputElem.value, cities);
                renderSuggestions(suggestionContainer, matches, inputElem);
            });

            inputElem.addEventListener('blur', () => {
                setTimeout(() => {
                    suggestionContainer.style.display = 'none';
                }, 150);
            });

            inputElem.addEventListener('focus', async () => {
                if (inputElem.value.trim() === '') return;
                const cities = await fetchCities();
                const matches = filterCities(inputElem.value, cities);
                renderSuggestions(suggestionContainer, matches, inputElem);
            });
        }

        setupAutocomplete(departureInput, departureSuggestions);
        setupAutocomplete(destinationInput, destinationSuggestions);
    });

</script>

</@main.layout>