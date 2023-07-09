import Link from "next/link";
import NextImage from "~/components/ui/NextImage";
import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX CategoryCard
// --------------------------------------------------------------------------
type CategoryCardProps = {
    className?: string,
    name: string,
    image: string,
}

const CategoryCard = React.forwardRef<HTMLAnchorElement, CategoryCardProps>(
    (props, ref) => {
        return (
            <>
                <Link href={`/categories/${props.name}`}
                      ref={ref}
                      passHref={true}
                      className={clsx(props.className, "flex items-center justify-center text-center group")}>
                    <div>
                        <NextImage width={150}
                                   height={150}
                                   useSkeleton={true}
                                   className="w-32 h-32
                                              md:w-40 md:h-40
                                              lg:w-48 lg:h-48
                                              rounded-2xl
                                              md:rounded-2xl"
                                   imgClassName="h-full w-full
                                                 rounded-2xl
                                                 md:rounded-2xl"
                                   src={props.image}
                                   alt={""}/>
                        <p className="text-center
                                      font-bold
                                      group-hover:text-blue-600
                                      mt-3">
                            {props.name}
                        </p>
                    </div>
                </Link>
            </>
        )
    });
CategoryCard.displayName = "CategoryCard";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {
    type CategoryCardProps,
    CategoryCard
}