import {AuthLayout} from "~/pages/auth/AuthLayout";
import {ForgotPasswordForm} from "~/components/forms/auth/ForgotPasswordForm";
import React from "react";

export default function ForgotPassword() {
    return (
        <AuthLayout>
            <div className="text-xl leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                <p className="font-bold">
                    Reset password
                </p>
                <p className="text-sm text-gray-400">
                    Enter your email address and we will send you a verification code
                </p>
            </div>
            <div className="space-y-4 md:space-y-6 form-control">
                <ForgotPasswordForm/>
            </div>
        </AuthLayout>
    )
}