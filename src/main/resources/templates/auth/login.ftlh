<#import "../layout.ftlh" as main>
<@main.layout>
<section class=" text-center text-lg-start">
    <style>
        .rounded-t-5 {
            border-top-left-radius: 0.5rem;
            border-top-right-radius: 0.5rem;
        }

        @media (min-width: 992px) {
            .rounded-tr-lg-0 {
                border-top-right-radius: 0;
            }

            .rounded-bl-lg-5 {
                border-bottom-left-radius: 0.5rem;
            }
        }
    </style>
    <div class="card m-3">
        <div class="row g-0 d-flex align-items-center">
            <div class="col-lg-4 d-none d-lg-flex">
                <img src="https://mdbootstrap.com/img/new/ecommerce/vertical/004.jpg" alt="Trendy Pants and Shoes"
                     class="w-100 rounded-t-5 rounded-tr-lg-0 rounded-bl-lg-5"/>
            </div>
            <div class="col-lg-8">
                <div class="card-body py-5 px-md-5">

                    <form id="login-form" action="/auth/login" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="email" id="form2Example1" class="form-control" name="username"/>
                            <label class="form-label" for="form2Example1">Email address</label>
                        </div>

                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="password" id="form2Example2" class="form-control" name="password"/>
                            <label class="form-label" for="form2Example2">Password</label>
                        </div>

                        <button type="submit" data-mdb-button-init data-mdb-ripple-init
                                class="btn btn-primary btn-block mb-4">Sign in
                        </button>

                    </form>

                </div>
            </div>
        </div>
    </div>
</section>
</@main.layout>