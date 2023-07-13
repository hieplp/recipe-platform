export default interface Recipe {
    recipeId: string,
    image: string,
    title: string,
    description?: string,
    rating?: number,
    time?: string,
    totalComments?: number,
    //
    authorId?: string,
    authorName?: string,
    authorAvatar?: string,
}