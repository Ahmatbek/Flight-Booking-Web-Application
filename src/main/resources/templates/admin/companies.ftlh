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
            vertical-align: middle;
        }

        tr:hover {
            background-color: #f1f5f9;
        }

        button, .btn-link {
            background-color: #2563eb;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.2s;
            text-decoration: none;
            display: inline-block;
            font-size: 0.9rem;
        }

        button:hover, .btn-link:hover {
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

        /* spacing buttons */
        .actions form, .actions a {
            display: inline-block;
            margin-right: 8px;
            margin-bottom: 0;
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
            <th>More</th>
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
                        <span class="status-active">Active</span>
                    <#else>
                        <span class="status-frozen">Frozen</span>
                    </#if>
                </td>
                <td class="actions">
                    <a href="/admin/flights/${company.id}">View Details</a>

                    <#if company.enabled>
                        <form action="/admin/freeze/${company.id}" method="post" style="display:inline;">
                            <#if _csrf??>
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                       value="${(_csrf.token)!'csrf-token'}"/>
                            </#if>
                            <button type="submit">Freeze</button>
                        </form>
                    <#else>
                        <form action="/admin/unfreeze/${company.id}" method="post" style="display:inline;">
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
