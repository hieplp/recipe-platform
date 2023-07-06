import React from "react";
import NextImage from "~/components/ui/NextImage";
import {AlignVerticalText} from "~/components/ui/Text";
import {CalendarIcon, ChatBubbleBottomCenterIcon} from "@heroicons/react/24/outline";
import {clsx} from "clsx";
import Link from "next/link";

// --------------------------------------------------------------------------
// XXX RecipeCard
// --------------------------------------------------------------------------
type RecipeCardProps = {
    recipeId: string,
    className?: string,
    title: string,
    image: string,
    rating: number,
    name: string,
    avatar: string,
    time: string,
    totalComments: number,
};
const RecipeCard = React.forwardRef<HTMLAnchorElement, RecipeCardProps>(
    ({
         recipeId,
         className,
         title,
         image,
         rating,
         name,
         avatar,
         time,
         totalComments
     }, ref) => {
        return (
            <Link ref={ref}
                  href={`/recipe/${recipeId}`}
                  passHref={true}
                  className={clsx(className, "card w-full bg-base-100 shadow-xl")}>
                <figure className="w-full h-44">
                    <NextImage src={image}
                               useSkeleton={true}
                               height={550}
                               width={760}
                               className="h-64 w-full"
                               imgClassName="h-full w-full"
                               alt=""/>
                </figure>
                <div className="card-body">
                    <RecipeCardRating rating={rating}/>

                    <h2 className="card-title">
                        {title}
                    </h2>

                    <div className="flex mt-3">
                        <div className="avatar">
                            <NextImage src={avatar}
                                       height={50}
                                       width={50}
                                       className="w-10 rounded-full"
                                       imgClassName="h-full w-full"
                                       alt=""/>
                        </div>

                        <AlignVerticalText text={name}
                                           className="h-full font-bold ml-3"/>
                    </div>

                    <div className="card-actions
                                    justify-end
                                    text-sm
                                    text-gray-500">
                        <div className="flex mr-3">
                            <ChatBubbleBottomCenterIcon className="w-5 h-5 mr-1"/>
                            <p>{totalComments < 1000 ? totalComments : '999+'}</p>
                        </div>
                        <div className="flex">
                            <CalendarIcon className="w-5 h-5 mr-1"/>
                            <p>{time}</p>
                        </div>
                    </div>
                </div>
            </Link>
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
                 className={clsx(className, "rating gap-1")}>
                {
                    ratingList.map((item, index) => {
                        return (
                            <input key={index}
                                   checked={rating == item.rating}
                                   disabled={true} type="radio"
                                   className={`mask mask-heart ${item.color}`}/>
                        )
                    })
                }
            </div>
        )
    });
RecipeCardRating.displayName = "RecipeCardRating";


// --------------------------------------------------------------------------
// XXX RecipeCardList
// --------------------------------------------------------------------------
type RecipeCardListProps = {
    className?: string,
    recipes: RecipeCardProps[]
}
const RecipeCardList = React.forwardRef<HTMLDivElement, RecipeCardListProps>(
    ({
         className,
         recipes
     }, ref) => {
        return (
            <div ref={ref}
                 className={clsx(className, "grid")}>
                {
                    recipes.map((recipe, index) => {
                        return (
                            <RecipeCard key={index}
                                        recipeId={recipe.recipeId}
                                        title={recipe.title}
                                        image={recipe.image}
                                        rating={recipe.rating}
                                        name={recipe.name}
                                        avatar={recipe.avatar}
                                        time={recipe.time}
                                        totalComments={recipe.totalComments}/>
                        )
                    })
                }
            </div>
        )
    });
RecipeCardList.displayName = "RecipeCardList";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {RecipeCard, RecipeCardList}