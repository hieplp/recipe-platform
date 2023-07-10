// --------------------------------------------------------------------------
// XXX RecipeCardList
// --------------------------------------------------------------------------
import React from "react";
import {clsx} from "clsx";
import {RecipeCard, type RecipeCardProps} from "~/components/recipe/RecipeCard";

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
            <>
                <div ref={ref}
                     className={clsx(className, "grid mb-12")}>
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
            </>
        )
    });
RecipeCardList.displayName = "RecipeCardList";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {RecipeCardList}