import { FunctionComponent } from "react";

interface Props {
    author: string,
    text: string,
    rating: number
}



export const Reviews: FunctionComponent<Props> = ({reviews}) => {
    return (
        <div>
            {
                reviews.map((review) => {
                    <div key={review.id}>
                        <span>{review.author}</span>
                        <span>{review.text}</span>
                        <span>{review.rating}</span>
                    </div>
                })
            }
        </div>
    );
};