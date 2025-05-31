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

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
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

        img {
            border-radius: 6px;
            max-height: 50px;
        }

        button {
            background-color: #2563eb;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        button:hover {
            background-color: #1d4ed8;
        }

        .status-active {
            color: green;
            font-weight: bold;
        }

        .status-frozen {
            color: red;
            font-weight: bold;
        }

        .no-logo {
            color: #9ca3af;
            font-style: italic;
        }
    </style>
<h1>Registered Tourist Companies</h1>

<table border="1" cellpadding="10" cellspacing="0">
    <thead>
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Email</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <#list companies as company>
        <tr>
            <td>${company_index + 1}</td>
            <td>${company.name}</td>
            <td>${company.email}</td>
            <td>
                <#if company.enabled>
                    <span style="color: green;">Active</span>
                <#else>
                    <span style="color: red;">Frozen</span>
                </#if>
            </td>
            <td>
                <#if company.enabled>
                    <form action="/admin/freeze/${company.id}" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <button type="submit">Freeze</button>
                    </form>
                <#else>
                    <form action="/admin/unfreeze/${company.id}" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <button type="submit">Unfreeze</button>
                    </form>
                </#if>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
</@main.layout>