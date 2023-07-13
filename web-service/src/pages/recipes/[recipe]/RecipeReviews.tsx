import React from "react";
import NextImage from "~/components/ui/NextImage";
import {RecipeCardRating} from "~/components/recipe/RecipeCard";
import {HeartIcon} from "@heroicons/react/24/outline";
import {clsx} from "clsx";
import {LineBreak} from "~/components/ui/Line";

// --------------------------------------------------------------------------
// XXX RecipeReview
// --------------------------------------------------------------------------

type RecipeReviewProps = {
    className?: string,
    avatar: string,
    name: string,
    time: string,
    rating: number,
    review: string,
    likes: number,
    isLiked: boolean,
}

const RecipeReview = React.forwardRef<HTMLDivElement, RecipeReviewProps>(
    (props, ref) => {
        return (
            <div ref={ref}
                 className={clsx(props.className, "grid grid-cols-12")}>
                <div className="col-span-2 md:col-span-1
                                h-10 avatar
                                flex justify-center">
                    <NextImage src={props.avatar}
                               className="w-10 h-10"
                               imgClassName="rounded-full"
                               alt=""
                               width={50}
                               height={50}/>
                </div>

                <div className="col-span-10 md:col-span-11">
                    <p className="font-bold">
                        {props.name}
                    </p>

                    <p className="text-gray-300 text-sm">
                        {props.time}
                    </p>

                    <p className="mt-2">
                        {props.review}
                    </p>

                    <RecipeCardRating className="mt-2"
                                      rating={props.rating}/>

                    <div className={clsx(props.isLiked ? "text-red-600" : "text-gray-300", "text-sm mt-2")}>
                        <div className="flex items-center hover:cursor-pointer hover:text-red-600">
                            <HeartIcon className="w-5 h-5 mr-1"/>
                            <p>{props.likes}</p>
                        </div>
                    </div>

                </div>
            </div>
        )
    });
RecipeReview.displayName = "RecipeReview";

// --------------------------------------------------------------------------
// XXX RecipeReviews
// --------------------------------------------------------------------------

type RecipeReviewsProps = {
    className?: string,
    reviews: RecipeReviewProps[],
}

const RecipeReviews = React.forwardRef<HTMLDivElement, RecipeReviewsProps>(
    (props, ref) => {
        return (
            <div ref={ref}
                 className={clsx(props.className, "space-y-5")}>
                {props.reviews.map((review, index) => (
                    <>
                        <RecipeReview key={index}
                                      className="space-y-2"
                                      {...review}/>
                        <LineBreak/>
                    </>
                ))}
            </div>
        )
    });
RecipeReviews.displayName = "RecipeReviews";

// --------------------------------------------------------------------------
// XXX Exports
// --------------------------------------------------------------------------
export {
    RecipeReviews,
    type RecipeReviewProps,
};
