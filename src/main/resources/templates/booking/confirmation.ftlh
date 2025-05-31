<#import "../layout.ftlh" as main>
<@main.layout>
<div class="container mt-5">
    <div class="card">
        <div class="card-header bg-success text-white">
            <h3 class="mb-0">Booking Confirmed!</h3>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-6">
                    <h4>Flight Details</h4>
                    <p><strong>Flight Number:</strong> ${ticket.flight.number}</p>
                    <p><strong>From:</strong> ${ticket.flight.fromCity}</p>
                    <p><strong>To:</strong> ${ticket.flight.toCity}</p>
                    <p><strong>Departure:</strong> ${ticket.flight.departureTime?string("dd MMM yyyy HH:mm")}</p>
                    <p><strong>Arrival:</strong> ${ticket.flight.arrivalTime?string("dd MMM yyyy HH:mm")}</p>
                </div>
                <div class="col-md-6">
                    <h4>Ticket Details</h4>
                    <p><strong>Ticket ID:</strong> ${ticket.id}</p>
                    <p><strong>Class:</strong> ${ticket.ticketClassName}</p>
                    <p><strong>Price:</strong> $${ticket.price}</p>
                </div>
            </div>
        </div>
        <div class="card-footer">
            <a href="/" class="btn btn-primary">Return to Home</a>
            <a href="/search" class="btn btn-secondary">Search More Flights</a>
        </div>
    </div>
</div>

<style>
    .card {
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        border: none;
    }
    
    .card-header {
        border-bottom: none;
    }
    
    .card-body {
        padding: 2rem;
    }
    
    .card-footer {
        background-color: #f8f9fa;
        border-top: 1px solid #dee2e6;
        padding: 1rem;
    }
    
    .btn {
        margin-right: 10px;
    }
</style>
</@main.layout> 