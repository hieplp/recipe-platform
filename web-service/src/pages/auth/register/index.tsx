import React from "react";
import {RegisterForm} from "~/components/forms/auth/RegisterForm";
import Link from "next/link";
import {AuthLayout} from "~/pages/auth/AuthLayout";

export default function Register() {
    return (
        <AuthLayout>
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
        </AuthLayout>
    )
}