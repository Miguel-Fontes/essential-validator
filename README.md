# essentials-validation | [![Build Status](https://travis-ci.org/Miguel-Fontes/essentials-validation.svg?branch=master)](https://travis-ci.org/Miguel-Fontes/essentials-validation)

A library providing utilities for validation of entities and data.

## Contents

<!-- TOC -->

- [essentials-validation | [![Build Status](https://travis-ci.org/Miguel-Fontes/essentials-validation.svg?branch=master)](https://travis-ci.org/Miguel-Fontes/essentials-validation)](#essentials-validation--build-statushttpstravis-ciorgmiguel-fontesessentials-validationsvgbranchmasterhttpstravis-ciorgmiguel-fontesessentials-validation)
    - [Contents](#contents)
    - [Motivation](#motivation)
    - [Features](#features)
        - [Validated Entity Superclass](#validated-entity-superclass)

<!-- /TOC -->

## Motivation

I've started this library motivated by the validation practices promoted on DDD, and the lack of a easy way to replicate a validation structure in several projects. The main objectives are:

- Provide a way to validate pre and post conditions of entities that use **immutability**;
- Garantee that a **immutable** class obeys a set of invariants;
- Be lightweight and easy to use;
- Leverage the power of existing validation tools on Java ecossystem;

## Features

### Validated Entity Superclass

TBD