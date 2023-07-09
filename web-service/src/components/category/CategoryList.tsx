import {CategoryCard, type CategoryCardProps} from "~/components/category/CategoryCard";
import React from "react";

// --------------------------------------------------------------------------
// XXX CategoryList
// --------------------------------------------------------------------------
type CategoryListProps = {
    className?: string,
    categories: CategoryCardProps[],
}

const CategoryList = React.forwardRef<HTMLDivElement, CategoryListProps>(
    (props, ref) => {
        let className = props.className;
        if (!className) {
            className = "";
        }

        return (
            <div ref={ref} className={className}>
                {
                    props.categories.map(({name, image}, index) => (
                        <CategoryCard name={name}
                                      image={image}
                                      key={index}/>
                    ))
                }
            </div>
        )
    });
CategoryList.displayName = "CategoryList";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {CategoryList}