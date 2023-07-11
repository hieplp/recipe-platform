import {Checklist, type ChecklistItemProps} from "~/components/ui/Checklist";
import {clsx} from "clsx";
import React from "react";

type RecipeIngredientsProps = {
    className?: string;
    title: string;
    ingredients: ChecklistItemProps[];
}

const RecipeIngredients = React.forwardRef<HTMLDivElement, RecipeIngredientsProps>(
    (props, ref) => {
        return (
            <div ref={ref}
                 className={clsx(props.className, "")}>
                <p className="text-md font-bold">
                    {props.title}
                </p>
                <Checklist list={props.ingredients}
                           itemClassName="hover:text-blue-700 hover:font-bold"
                />
            </div>
        )
    })
RecipeIngredients.displayName = "RecipeIngredients";

export default RecipeIngredients;