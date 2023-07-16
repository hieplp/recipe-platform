import React from "react";
import {ArrowTrendingUpIcon, ArrowUpOnSquareIcon, BookmarkIcon} from "@heroicons/react/24/outline";
import {OnlyIconButton} from "~/components/ui/Button";
import {clsx} from "clsx";

type RecipeTitleProps = {
    className?: string,
    percent: number,
}

const RecipeTitle = React.forwardRef<HTMLDivElement, RecipeTitleProps>(
    (props, ref) => {
        return (
            <div ref={ref} className={clsx(
                props.className,
                "dark:text-white"
            )}>
                <div className="flex">
                    <div className="flex space-x-2">
                        <ArrowTrendingUpIcon className="w-8 h-8"/>
                        <p className="flex items-center">
                            {props.percent}% would make this again
                        </p>
                    </div>

                    <div className="flex space-x-2 ml-auto">
                        <OnlyIconButton>
                            <ArrowUpOnSquareIcon className="w-8 h-8"/>
                        </OnlyIconButton>

                        <OnlyIconButton>
                            <BookmarkIcon className="w-8 h-8"/>
                        </OnlyIconButton>
                    </div>
                </div>

                <p className="text-2xl font-bold">
                    Strawberry Shortcake
                </p>
            </div>
        )
    }
)
RecipeTitle.displayName = "RecipeTitle"

export default RecipeTitle;