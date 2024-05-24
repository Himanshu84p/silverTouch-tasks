import React from 'react'
import { Outlet } from 'react-router-dom'
import Header from './Header'


function Layout() {
  return (
    <div>
        <Header /> 
        //outlet dynamic component as per route
        <Outlet /> 
    </div>
  )
}

export default Layout