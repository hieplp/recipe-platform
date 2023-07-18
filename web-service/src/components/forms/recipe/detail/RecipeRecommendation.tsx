import React from "react";
import type Recipe from "~/types/Recipe";
import {clsx} from "clsx";
import {RecipeCardMini} from "~/components/recipe/RecipeCard";

// --------------------------------------------------------------------------
// XXX RecipeRecommendation
// --------------------------------------------------------------------------

const RecipeRecommendation = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement> & { recipes: Recipe[] }
>(({
       className,
       ...props
   }, ref) => (
    <div ref={ref}
         className={clsx(
             "grid",
             "grid-cols-1 md:grid-cols-3 xl:grid-cols-4",
             "gap-5 md:gap-5",
             className
         )}>
        {
            props.recipes.map((recipe) => (
                <RecipeCardMini key={recipe.recipeId}
                                {...recipe}/>
            ))
        }
    </div>
));
RecipeRecommendation.displayName = "RecipeRecommendation";

// --------------------------------------------------------------------------
// XXX Exports
// --------------------------------------------------------------------------
export {RecipeRecommendation}