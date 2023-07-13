import React from "react";
import Link from "next/link";
import {RecipeCardRating} from "~/components/recipe/RecipeCard";
import {PrimaryButton, SecondaryButton} from "~/components/ui/Button";
import {clsx} from "clsx";

type RecipeRatingProps = {
    className?: string,
    isLogin?: boolean,
}

const RecipeRating = React.forwardRef<HTMLDivElement, RecipeRatingProps>(
    (props, ref) => {
        //
        const cancel = () => {
            console.log("Cancel");
        };

        //
        const share = () => {
            console.log("Share");
        }

        return (
            <div ref={ref}
                 className={clsx(props.className, "space-y-3")}>
                <div className="md:flex items-center">
                    <p className="text-2xl font-bold">
                        Share your feedback
                    </p>

                    {
                        !props.isLogin &&
                        <div className="flex space-x-1 ml-auto">
                            <Link href="/auth/login"
                                  className="text-primary hover:underline">
                                Login
                            </Link>
                            <p className="">to share your feedback</p>
                        </div>
                    }
                </div>

                <div className="space-y-2">
                    <div className="">
                        <p className="label">
                            How was your experience with this recipe?
                        </p>

                        <RecipeCardRating className="ml-2"
                                          rating={3}/>
                    </div>

                    <div className="form-control">
                        <label className="label">
                            Your Feedback
                        </label>
                        <textarea className="textarea textarea-bordered h-24"
                                  placeholder="This recipe is good <3"></textarea>
                    </div>

                    <div className="w-full flex justify-end space-x-2">
                        <SecondaryButton className="" onClick={cancel}>
                            Cancel
                        </SecondaryButton>
                        <PrimaryButton className="" onClick={share}>
                            Share
                        </PrimaryButton>
                    </div>
                </div>
            </div>
        )
    });
RecipeRating.displayName = "RecipeRating";

export default RecipeRating;