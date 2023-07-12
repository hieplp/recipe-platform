import React from "react";
import {CalendarIcon, ChatBubbleLeftIcon} from "@heroicons/react/24/outline";
import {RecipeCardRating} from "~/components/recipe/RecipeCard";
import NextImage from "~/components/ui/NextImage";

type RecipeSubTitleProps = {
    className?: string,
    avatar: string,
    author: string,
    date: string,
    totalComments: number,
}

const RecipeSubTitle = React.forwardRef<HTMLDivElement, RecipeSubTitleProps>(
    (props, ref) => {
        return (
            <div ref={ref}
                 className="grid md:flex
                            items-center
                            space-x-1 md:space-x-5
                            space-y-3 md:space-y-0">
                <div className="avatar space-x-2">
                    <NextImage src={props.avatar}
                               alt=""
                               className="w-10"
                               imgClassName="rounded-full"
                               width={50}
                               height={50}/>

                    <p className="flex items-center font-bold">
                        {props.author}
                    </p>
                </div>

                <div className="flex">
                    <div className="flex items-center space-x-2">
                        <div className="flex items-center font-bold space-x-1">
                            <CalendarIcon className="w-5 h-5 mr-1"/>
                            <p>{props.date}</p>
                        </div>

                        <div className="flex items-center font-bold space-x-1">
                            <ChatBubbleLeftIcon className="w-5 h-5 mr-1"/>
                            <p>{props.totalComments < 1000 ? props.totalComments : '999+'}</p>
                        </div>
                    </div>

                    <RecipeCardRating className="ml-auto md:ml-5" rating={3}/>
                </div>

            </div>
        )
    });
RecipeSubTitle.displayName = "RecipeSubTitle";

export default RecipeSubTitle;