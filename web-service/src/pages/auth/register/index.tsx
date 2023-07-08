import {CenterLayout} from "~/components/layouts/CenterLayout";
import {BrandIcon} from "~/components/ui/Icon";
import React from "react";
import {RegisterForm} from "~/components/forms/RegisterForm";
import Link from "next/link";

export default function Register() {
    return (
        <CenterLayout>
            <div className="w-full md:w-3/4
                           flex flex-col
                           items-center justify-center
                           px-6 py-8 mx-auto
                           md:h-screen lg:py-0">
                <BrandIcon/>
                <div className="w-full bg-white
                                rounded-lg shadow
                                mt-5 md:mt-5
                                sm:max-w-md
                                xl:p-0
                               ">
                    <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
                        <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                            Sign up to your account
                        </h1>
                        <div className="space-y-4 md:space-y-6 form-control">

                            <RegisterForm/>

                            <p className="text-sm text-center font-light text-gray-500">
                                Already have an account?
                                <Link href="/auth/login"
                                      className="font-medium
                                                 text-primary-600
                                                 ml-1
                                                 hover:underline
                                                 dark:text-primary-500">
                                    Sign in
                                </Link>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </CenterLayout>
    )
}