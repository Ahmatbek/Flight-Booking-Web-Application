<#import "../layout.ftlh" as main>
<@main.layout ; spring>
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

                        <form id="login-form" action="/admin/create" method="post">
                            <#if _csrf??>
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                       value="${(_csrf.token)!'csrf-token'}"/>
                            </#if>
                            <div data-mdb-input-init class="form-outline mb-4">
                                <label class="form-label" for="form2Example1">Name</label>
                                <@spring.formInput "userDto.name" 'id="form3Example0cg" class="form-control form-control-lg"' />
                                <@spring.showErrors "<br>" "error text-danger" />
                            </div>

                            <div data-mdb-input-init class="form-outline mb-4">
                                <label class="form-label" for="form2Example1">Email address</label>
                                <@spring.formInput "userDto.email" 'id="form3Example3cg" class="form-control form-control-lg"' />
                                <@spring.showErrors "<br>" "error text-danger" />
                            </div>

                            <div data-mdb-input-init class="form-outline mb-4">
                                <label class="form-label" for="form2Example2">Password</label>
                                <@spring.formInput "userDto.password" 'id="form3Example4cg" class="form-control form-control-lg"''password' />
                                <@spring.showErrors "<br>" "error text-danger" />
                            </div>
                            <input type="hidden" name="userDto.authority" value="COMPANY">


                            <button type="submit" data-mdb-button-init data-mdb-ripple-init
                                    class="btn btn-primary btn-block mb-4">Create Company
                            </button>

                        </form>

                    </div>
                </div>
            </div>
        </div>
    </section>
</@main.layout>