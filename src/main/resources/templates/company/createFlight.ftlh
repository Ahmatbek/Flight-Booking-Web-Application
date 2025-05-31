<#import "../layout.ftlh" as main>
<@main.layout; spring>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9fafb;
            margin: 0;
            padding: 40px;
        }

        h1 {
            color: #1f2937;
            margin-bottom: 20px;
            text-align: center;
        }

        form {
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            max-width: 600px;
            margin: 0 auto;
        }

        label {
            display: block;
            font-size: 14px;
            color: #333;
            margin-bottom: 8px;
        }

        input[type="text"], input[type="datetime-local"], select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 4px;
            border: 1px solid #e5e7eb;
            font-size: 14px;
        }

        button {
            background-color: #2563eb;
            color: white;
            border: none;
            padding: 10px 16px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.2s;
            width: 100%;
            font-size: 16px;
        }

        button:hover {
            background-color: #1d4ed8;
        }

        .error-message {
            color: red;
            font-size: 14px;
            margin-top: -10px;
        }
    </style>

    <h1>Create New Flight</h1>

    <form action="/company/createFlight" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>

        <label for="number">Flight Number</label>
        <@spring.formInput "flightDto.number" 'id="number" class="form-control form-control-lg"' />
        <@spring.showErrors "<br>" "error-message" />

        <label for="fromCity">From City</label>
        <@spring.formInput "flightDto.fromCity" 'id="fromCity" class="form-control form-control-lg"' />
        <@spring.showErrors "<br>" "error-message" />

        <label for="toCity">To City</label>
        <@spring.formInput "flightDto.toCity" 'id="toCity" class="form-control form-control-lg"' />
        <@spring.showErrors "<br>" "error-message" />

        <label for="departureTime">Departure Time</label>
        <@spring.formInput "flightDto.departureTime" 'id="departureTime" type="datetime-local" class="form-control form-control-lg"' 'date' />
        <@spring.showErrors "<br>" "error-message" />

        <label for="arrivalTime">Arrival Time</label>
        <@spring.formInput "flightDto.arrivalTime" 'id="arrivalTime" type="datetime-local" class="form-control form-control-lg"' 'date' />
        <@spring.showErrors "<br>" "error-message" />

        <button type="submit">Create Flight</button>

    </form>

</@main.layout>
