<#import "../layout.ftlh" as main>
<@main.layout; spring>

    <style>
        h1 {
            text-align: center;
            margin-bottom: 25px;
            color: #007bff;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        table {
            width: 90%;
            margin: 0 auto 40px auto;
            border-collapse: separate;
            border-spacing: 0 12px; /* spacing between rows */
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            border-radius: 12px;
            overflow: hidden;
            background-color: #fff;
        }

        thead tr {
            background-color: #007bff;
            color: #fff;
            text-transform: uppercase;
            font-weight: 700;
            font-size: 0.95rem;
            letter-spacing: 1px;
        }

        th, td {
            padding: 14px 18px;
            text-align: left;
            vertical-align: middle;
        }

        tbody tr {
            background-color: #fefefe;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
            transition: background-color 0.3s ease, transform 0.2s ease;
            border-radius: 10px;
        }

        tbody tr:hover {
            background-color: #e9f5ff;
            transform: translateY(-3px);
        }

        tbody tr td {
            border-bottom: none;
        }

        /* Alternate row colors */
        tbody tr:nth-child(even) {
            background-color: #f9faff;
        }

        /* Center booked column */
        td:nth-child(7) {
            text-align: center;
            font-weight: 600;
            color: #28a745;
        }

        /* If booked = No, show red */
        td:nth-child(7):contains("No") {
            color: #dc3545;
            font-weight: 700;
        }

        /* Responsive */
        @media (max-width: 768px) {
            table, thead, tbody, th, td, tr {
                display: block;
                width: 100%;
            }
            thead tr {
                display: none;
            }
            tbody tr {
                margin-bottom: 20px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.1);
                border-radius: 8px;
                background-color: #fff;
                padding: 15px 10px;
            }
            tbody tr td {
                padding-left: 50%;
                position: relative;
                text-align: right;
                border-bottom: 1px solid #eee;
            }
            tbody tr td::before {
                position: absolute;
                left: 15px;
                width: 45%;
                white-space: nowrap;
                font-weight: 700;
                text-align: left;
                color: #555;
                content: attr(data-label);
            }
            tbody tr td:last-child {
                border-bottom: none;
            }
        }

        td.booked {
            color: #28a745;
            font-weight: 600;
            text-align: center;
        }

        td.not-booked {
            color: #dc3545;
            font-weight: 700;
            text-align: center;
        }

    </style>
<h1>Tickets List</h1>

<table border="1" cellpadding="5" cellspacing="0">
    <thead>
    <tr>
        <th>ID</th>
        <th>Seat Number</th>
        <th>Price</th>
        <th>Flight</th>
        <th>Ticket Class</th>
        <th>Booked</th>
    </tr>
    </thead>
    <tbody>
    <#list tickets as ticket>
        <tr>
            <td>${ticket.id}</td>
            <td>${ticket.seatNumber}</td>
            <td>${ticket.price}</td>
            <td>
                ${ticket.flight.number}
            </td>
            <td>${ticket.ticketClass}</td>
            <td class="${ticket.booked?string('booked', 'not-booked')}">
                <#if ticket.booked>
                    Yes
                <#else>
                    No
                </#if>
            </td>

        </tr>
    </#list>
    </tbody>
</table>

</@main.layout>