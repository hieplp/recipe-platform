import React from "react";
import NextImage from "~/components/ui/NextImage";
import {AlignVerticalText} from "~/components/ui/Text";
import {CalendarIcon, ChatBubbleBottomCenterIcon} from "@heroicons/react/24/outline";
import {clsx} from "clsx";
import {Card, CardContent, CardFooter, CardImage, CardTitle} from "~/components/ui/Card";
import type Recipe from "~/types/Recipe";

// --------------------------------------------------------------------------
// XXX RecipeCard
// --------------------------------------------------------------------------
const RecipeCard = React.forwardRef<
    HTMLAnchorElement,
    React.HTMLAttributes<HTMLAnchorElement> & Recipe
>(({
       className,
       ...props
   }, ref) => {
    return (
        <Card ref={ref}
              href={`/recipes/${props.recipeId}`}
              passHref={true}
              className={clsx(className, "bg-base-100 shadow-xl")}>
            <CardImage src={props.image}
                       className="h-44 w-full"
            />
            <CardContent>
                <RecipeCardRating rating={props.rating ? props.rating : 0}/>

                <CardTitle>
                    {props.title}
                </CardTitle>

                <div className="flex mt-3">
                    <div className="avatar">
                        <NextImage src={props.authorAvatar ? props.authorAvatar : '/avatar.jpg'}
                                   height={50}
                                   width={50}
                                   className="w-10 rounded-full"
                                   imgClassName="h-full w-full"
                                   alt=""/>
                    </div>

                    <AlignVerticalText text={props.authorName ? props.authorName : 'Anonymous'}
                                       className="h-full font-bold ml-3"/>
                </div>
            </CardContent>

            <CardFooter className="card-actions
                                   justify-end
                                   text-sm
                                   text-gray-500">
                <div className="flex mr-2 space-x-1">
                    <ChatBubbleBottomCenterIcon className="w-5 h-5"/>
                    <p>
                        {
                            props.totalComments
                                ? (props.totalComments < 1000 ? props.totalComments : '999+')
                                : 0
                        }
                    </p>
                </div>
                <div className="flex space-x-1">
                    <CalendarIcon className="w-5 h-5"/>
                    <p>{props.time}</p>
                </div>
            </CardFooter>
        </Card>
    )
});
RecipeCard.displayName = "RecipeCard";

// --------------------------------------------------------------------------
// XXX RecipeCard - Rating
// --------------------------------------------------------------------------
type RecipeCardRatingProps = {
    className?: string,
    rating: number,
}

const RecipeCardRating = React.forwardRef<HTMLDivElement, RecipeCardRatingProps>(
    ({
         className,
         rating,
     }, ref) => {
        const ratingList = [
            {
                rating: 1,
                color: "bg-red-400"
            },
            {
                rating: 2,
                color: "bg-orange-400"
            },
            {
                rating: 3,
                color: "bg-yellow-400"
            },
            {
                rating: 4,
                color: "bg-lime-400"
            },
            {
                rating: 5,
                color: "bg-green-400"
            }
        ]
        return (
            <div ref={ref}
                 className={clsx(className, "rating rating-md gap-1")}>
                {
                    ratingList.map((item, index) => {
                        return (
                            <input key={index}
                                   checked={rating == item.rating}
                                   disabled={true}
                                   type="radio"
                                   className={`mask mask-heart ${item.color}`}/>
                        )
                    })
                }
            </div>
        )
    });
RecipeCardRating.displayName = "RecipeCardRating";

// --------------------------------------------------------------------------
// XXX RecipeCard - Mini
// --------------------------------------------------------------------------
const RecipeCardMini = React.forwardRef<
    HTMLAnchorElement,
    React.HTMLAttributes<HTMLAnchorElement> & Recipe
>(({
       className,
       ...props
   }, ref) => (
    <Card ref={ref}
          href={`/recipes/${props.recipeId}`}
          className={clsx(className, "space-y-2 group")}>
        <CardImage src={props.image}
                   className="h-44 xl:h-52 w-full rounded"/>

        <CardTitle className="group-hover:text-primary dark:text-white">
            {props.title}
        </CardTitle>
    </Card>
));
RecipeCardMini.displayName = "RecipeCardMini";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {
    RecipeCard,
    RecipeCardMini,
    RecipeCardRating,
}