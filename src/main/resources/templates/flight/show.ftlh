<#import "../layout.ftlh" as main>
<@main.layout; spring>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9fafb;
            padding: 40px;
            margin: 0;
        }

        h1 {
            text-align: center;
            color: #1f2937;
            margin-bottom: 24px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
        }

        thead {
            background-color: #4f46e5;
            color: white;
        }

        th, td {
            padding: 14px 16px;
            text-align: left;
            border-bottom: 1px solid #e5e7eb;
        }

        tr:hover {
            background-color: #f1f5f9;
        }

        .no-flights {
            text-align: center;
            color: #6b7280;
            margin-top: 40px;
        }
    </style>

    <h1>Flights List</h1>

    <#if flights?has_content>
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
                <th>Tickets</th>
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
                    <td><a href="/admin/tickets/${flight.id}">
                            tickets
                        </a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <div class="no-flights">No flights available for this company.</div>
    </#if>

</@main.layout>
