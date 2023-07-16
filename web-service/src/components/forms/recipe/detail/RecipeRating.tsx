import React from "react";
import Link from "next/link";
import {RecipeCardRating} from "~/components/recipe/RecipeCard";
import {PrimaryButton, SecondaryButton} from "~/components/ui/Button";
import {clsx} from "clsx";
import {Textarea} from "~/components/ui/Input";

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
                 className={clsx(
                     props.className,
                     "space-y-3",
                 )}>
                <div className="md:flex items-center dark:text-white">
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
                    <div className="dark:text-white">
                        <p className="label">
                            How was your experience with this recipe?
                        </p>

                        <RecipeCardRating className="ml-2"
                                          rating={3}/>
                    </div>

                    <Textarea label="Your Feedback"
                              inputClassName="h-32"
                              placeholder="This recipe is good <3"
                    />

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