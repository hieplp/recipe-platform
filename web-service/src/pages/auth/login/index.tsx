import React from "react";
import {LoginForm} from "~/components/forms/auth/LoginForm";
import Link from "next/link";
import {AuthLayout} from "~/pages/auth/AuthLayout";

export default function Login() {
    return (
        <AuthLayout>
            <h1 className="text-xl font-bold leading-tight tracking-tight md:text-2xl">
                Sign in to your account
            </h1>
            <div className="space-y-4 md:space-y-6 form-control">
                <LoginForm/>
                <p className="text-sm text-center font-light text-gray-500">
                    Don’t have an account yet?
                    <Link href="/auth/register"
                          className="font-medium
                                     text-primary-600
                                     ml-1
                                     hover:underline dark:text-primary-500">
                        Sign up
                    </Link>
                </p>
            </div>
        </AuthLayout>
    )
}