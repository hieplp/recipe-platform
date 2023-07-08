import React from "react";
import Link from "next/link";
import {PrimaryButton} from "~/components/ui/Button";

// --------------------------------------------------------------------------
// XXX RememberMe
// --------------------------------------------------------------------------
const RememberMe = React.forwardRef<HTMLDivElement>(({}, ref) => {
    return (
        <div ref={ref}
             className="flex items-center justify-between mb-5">
            <div className="flex items-start">
                <div className="flex items-center h-5">
                    <input id="remember"
                           aria-describedby="remember"
                           type="checkbox"
                           className="w-4 h-4
                                      border border-gray-300
                                      rounded bg-gray-50 focus:ring-3
                                      focus:ring-primary-300"
                           required/>
                </div>
                <div className="ml-3 text-sm">
                    <label htmlFor="remember"
                           className="text-gray-500 dark:text-gray-300">
                        Remember me
                    </label>
                </div>
            </div>
            <Link href="/auth/forgot-password"
                  className="text-sm font-medium text-primary-600 hover:underline dark:text-primary-500">
                Forgot password?
            </Link>
        </div>
    );
});
RememberMe.displayName = 'RememberMe';

// --------------------------------------------------------------------------
// XXX LoginForm
// --------------------------------------------------------------------------
export function LoginForm() {
    return (
        <section className="form-control">
            {/*Username*/}
            <div className="w-full mb-5">
                <label className="label">
                    <span className="label-text">Username</span>
                </label>
                <input type="text"
                       placeholder="hieplp"
                       className="input input-md w-full input-bordered"/>
            </div>

            {/*Password*/}
            <div className="w-full mb-5">
                <label className="label">
                    <span className="label-text">Password</span>
                </label>
                <input type="password"
                       placeholder="••••••••"
                       className="input input-md w-full input-bordered"/>
            </div>

            {/*Remember me*/}
            <RememberMe/>

            {/**/}
            <PrimaryButton className="w-full normal-case text-lg"
                           isLoading={false}>
                Login
            </PrimaryButton>
        </section>
    )
}