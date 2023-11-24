import { FilmDetails } from "@/components/Films/FilmDetails";
import Link from "next/link";
import React from "react";

export default function Home() {
  return (
    <div>
      <header />
      <FilmDetails title="Film 1" genre="comedy" seasonsCount={1}/>
      <footer />
    </div>
  );
}
