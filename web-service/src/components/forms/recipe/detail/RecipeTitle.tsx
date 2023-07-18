import React from "react";
import {ArrowTrendingUpIcon, BookmarkIcon, PencilSquareIcon, TrashIcon} from "@heroicons/react/24/outline";
import {OnlyIconButton} from "~/components/ui/Button";
import {clsx} from "clsx";
import {useRouter} from "next/router";
import {ConfirmationModal} from "~/components/ui/Modal";

type RecipeTitleProps = {
    className?: string,
    isOwner?: boolean,
    percent: number,
}

const RecipeTitle = React.forwardRef<HTMLDivElement, RecipeTitleProps>(
    (props, ref) => {

        const router = useRouter();
        const [isOpen, setIsOpen] = React.useState(false);

        const toggleDeleteModal = () => {
            setIsOpen(!isOpen);
        }

        const deleteRecipe = () => {
            console.log("delete recipe")
            toggleDeleteModal();
        }

        const goToUpdateRecipePage = () => {
            if (!props.isOwner) return;

            const recipeId = router.query.recipeId?.toString();
            if (!recipeId) return;

            void router.push(`/recipes/${recipeId}/update`);
        }

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
                        {
                            props.isOwner &&
                            <>
                                <OnlyIconButton onClick={toggleDeleteModal}>
                                    <TrashIcon className="w-8 h-8 hover:text-error"/>
                                </OnlyIconButton>

                                <OnlyIconButton onClick={goToUpdateRecipePage}>
                                    <PencilSquareIcon className="w-8 h-8 hover:text-warning"/>
                                </OnlyIconButton>
                            </>
                        }

                        <OnlyIconButton>
                            <BookmarkIcon className="w-8 h-8"/>
                        </OnlyIconButton>

                    </div>
                </div>

                <p className="text-2xl font-bold">
                    Strawberry Shortcake
                </p>


                <ConfirmationModal isOpen={isOpen}
                                   title="Delete Recipe"
                                   description="Are you sure you want to delete this recipe?"
                                   confirmLabel="Delete"
                                   onConfirm={deleteRecipe}
                                   cancelLabel="Cancel"
                                   onCancel={toggleDeleteModal}
                />
            </div>
        )
    }
)
RecipeTitle.displayName = "RecipeTitle"

export default RecipeTitle;