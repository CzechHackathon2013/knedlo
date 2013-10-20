//
//  SourceCategory.m
//  Knedlo
//
//  Created by Martin Wenisch on 10/20/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import "SourceCategory.h"

@implementation SourceCategory

- (id)initWithSource:(NSString *)name image:(UIImage *)image
{
    self = [super init];
    if (self) {
        self.categoryImage = image;
        self.categoryName = name;
    }
    return self;
}

@end
