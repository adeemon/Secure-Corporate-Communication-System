"use client";

import { FilmDetails } from "@/components/Films/FilmDetails";
import Link from "next/link";
import { useRouter } from "next/navigation";
import React, { useEffect } from "react";


export default function Home() {

  const router = useRouter();

  useEffect(() => {
    !sessionStorage.getItem("access_token") ? router.push("/login") : console.log("logged in");
  }, []);

  return (
    <div>
      <header />
      <FilmDetails title="Film 1" genre="comedy" seasonsCount={1} />
      <footer />
    </div>
  );
}
